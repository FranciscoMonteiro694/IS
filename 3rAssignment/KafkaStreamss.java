package Kafka;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.apache.kafka.streams.kstream.Materialized.as;

public class KafkaStreamss {

    public static void main(String[] args) throws InterruptedException, IOException {
        String topicSales = "Sales";
        String topicPurchases = "Purchases";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        // Stream topicSales
        KStream<String, String> sales = builder.stream(topicSales);
        // Stream topicPurchases
        KStream<String, String> purchases = builder.stream(topicPurchases);

        //sales.foreach((key, value) -> System.out.println(key + " => " + value));

        // Revenue per item (5)
        KTable<String, Integer> revenueperItem =  sales.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        revenueperItem.toStream().mapValues((k,v)->createJsonRevenueItem(k,v)).to("TopicRevenueItem", Produced.with(Serdes.String(),Serdes.String()));

        // Expenses per item (6)
        KTable<String, Integer> expensesesperItem =  purchases.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        expensesesperItem.toStream().mapValues((k,v)->createJsonExpensesItem(k,v)).to("TopicExpensesItem", Produced.with(Serdes.String(),Serdes.String()));

        // Profit per item (join) (7)
        KTable<String, Integer> profitperItem = revenueperItem.join(expensesesperItem,(r,e)->r-e);
        profitperItem.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicProfitItem", Produced.with(Serdes.String(),Serdes.String()));

        // Total revenue (8)
        KTable<String, Integer> totalRevenue = sales.mapValues(v->transform(v)).groupBy((k, v) -> "valor",Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        totalRevenue.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicTotalRevenue", Produced.with(Serdes.String(),Serdes.String()));

        // Total expenses (9)
        KTable<String, Integer> totalExpenses = purchases.mapValues(v->transform(v)).groupBy((k, v) -> "valor",Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        totalExpenses.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicTotalExpenses", Produced.with(Serdes.String(),Serdes.String()));

        // Total profit (join) (10)
        KTable<String, Integer> totalProfit = totalRevenue.join(totalExpenses,(r,e)->r-e);
        totalProfit.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicTotalProfit", Produced.with(Serdes.String(),Serdes.String()));

        // Total profit (join) (10)
        KTable<String, Integer> avgSpentbyitem = purchases.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->(v1+v2)/profitperItem.toStream().groupByKey().count());
        //KTable<String, Integer> avgSpentbyitem = profitperItem.mapValues((k,v)-> k + "===> "+v/)

        // Item with the highest profit (13)
        KTable<String, Integer> highestProfit = profitperItem.toStream().groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((aggValue, newValue) -> Math.max(aggValue, newValue));// erro aqui no reduce
        //KTable<String, Integer> highestProfit = profitperItem.toStream().groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).aggregate((aggValue, newValue) -> Math.max(aggValue, newValue));
        highestProfit.toStream().mapValues((k,v)-> k + "=>"+""+v).to("TopicMaxProfit", Produced.with(Serdes.String(),Serdes.String()));

        // Total revenue (last hour) (14)
        KTable<Windowed<String>, Integer> revenueLastHour = sales.mapValues(v->transform(v)).
                groupBy((k, v) -> "valor",Grouped.with(Serdes.String(),Serdes.Integer())).
                windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).
                reduce((aggval, newval) -> aggval + newval, Materialized.as("lixo1"));
        revenueLastHour.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to("TopicLastHourRevenue", Produced.with(Serdes.String(), Serdes.String()));

        // Total expenses (last hour) (15)
        KTable<Windowed<String>, Integer> expensesLastHour = purchases.mapValues(v->transform(v)).
                groupBy((k, v) -> "valor",Grouped.with(Serdes.String(),Serdes.Integer())).
                windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).
                reduce((aggval, newval) -> aggval + newval, Materialized.as("lixo2"));
        expensesLastHour.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to("TopicLastHourExpenses", Produced.with(Serdes.String(), Serdes.String()));

        // Total profit (last hour) (16)
        KTable<Windowed<String>, Integer> profitLastHour =  revenueLastHour.join(expensesLastHour,(r,e)->r-e);
        profitLastHour.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to("TopicLastHourProfit", Produced.with(Serdes.String(), Serdes.String()));






/*        KTable<String, Long> countlines = lines.
                groupByKey().
                reduce((oldval, newval) -> oldval + newval, Materialized.as(tablename));
        countlines.mapValues(v -> "" + v).toStream().to(outtopicname, Produced.with(Serdes.String(), Serdes.String()));*/







        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }

    public static String createJsonRevenueItem(String k,int value){
        System.out.println("O item "+k+" tem de revenue: "+value);
        String schema = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"item_id\"},{\"type\":\"int32\",\"optional\":true,\"field\":\"revenue\"}],\"optional\":false},\"payload\":{\"item_id\":"+k+",\"revenue\":"+ Integer.toString(value) +"}}";
        //System.out.println("Schema:         "+schema);
        return schema;

    }
    public static String createJsonExpensesItem(String k,int value){
        System.out.println("O item "+k+" tem de expense: "+value);
        String schema = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"item_id\"},{\"type\":\"int32\",\"optional\":true,\"field\":\"expenses\"}],\"optional\":false},\"payload\":{\"item_id\":"+k+",\"expenses\":"+ Integer.toString(value) +"}}";
        //System.out.println("Schema:         "+schema);
        return schema;

    }
    // Vai receber o value, pegar no units e price e multiplicar um pelo outro
    // Em principio funciona tanto para a revenue do item, como para a expenses
    private static int transform(String v) {
        JSONObject json = new JSONObject(v);
        System.out.println("Preço total: "+json.getInt("price")*json.getInt("units"));
        return json.getInt("price")*json.getInt("units");
    }


}
