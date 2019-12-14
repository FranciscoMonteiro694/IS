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

        //lines.foreach((key, value) -> System.out.println(key + " => " + value));

        // Revenue per item
        KTable<String, Integer> revenueperItem =  sales.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        revenueperItem.toStream().mapValues((k,v)->createJsonRevenueItem(k,v)).to("TopicRevenueItem", Produced.with(Serdes.String(),Serdes.String()));



        // Expenses per item
        KTable<String, Integer> expensesesperItem =  purchases.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        expensesesperItem.toStream().mapValues((k,v)->createJsonExpensesItem(k,v)).to("TopicExpensesItem", Produced.with(Serdes.String(),Serdes.String()));

        // Profit per item
        KTable<String, Integer> profitperItem =revenueperItem.join(expensesesperItem,(r,e)->r-e);
        profitperItem.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicProfitItem", Produced.with(Serdes.String(),Serdes.String()));

        /*// Total revenue
        KTable<String, Integer> totalRevenue = sales.mapValues(v->transform(v)).groupBy((k, v) -> "Revenue Total",Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        totalRevenue.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicTotalRevenue", Produced.with(Serdes.String(),Serdes.String()));

        // Total expenses
        KTable<String, Integer> totalExpenses = purchases.mapValues(v->transform(v)).groupBy((k, v) -> "Expenses Total",Grouped.with(Serdes.String(),Serdes.Integer())).reduce((v1,v2)->v1+v2);
        totalRevenue.toStream().mapValues((k,v)-> k + "=>"+v).to("TopicTotalExpenses", Produced.with(Serdes.String(),Serdes.String()));*/

        // Total profit

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }

    public static String createJsonRevenueItem(String k,int value){
        System.out.println("O item "+k+" tem de revenue: "+value);
        String schema = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"item_id\"},{\"type\":\"int32\",\"optional\":true,\"field\":\"revenue\"}],\"optional\":false},\"payload\":{\"item_id\":"+k+",\"revenue\":"+ value +"}}";
        System.out.println("Schema:         "+schema);
        return schema;

    }
    public static String createJsonExpensesItem(String k,int value){
        System.out.println("O item "+k+" tem de expense: "+value);
        String schema = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"item_id\"},{\"type\":\"int32\",\"optional\":true,\"field\":\"expenses\"}],\"optional\":false},\"payload\":{\"item_id\":"+k+",\"expenses\":"+ value +"}}";
        System.out.println("Schema:         "+schema);
        return schema;

    }
    // Vai receber o value, pegar no units e price e multiplicar um pelo outro
    // Em principio funciona tanto para a revenue do item, como para a expenses
    private static int transform(String v) {
        JSONObject json = new JSONObject(v);
        System.out.println("Preço: "+json.getInt("price"));
        System.out.println("Unidades: "+json.getInt("units"));
        System.out.println("Total: "+json.getInt("price")*json.getInt("units"));
        return json.getInt("price")*json.getInt("units");
    }


}
