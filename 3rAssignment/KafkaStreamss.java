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
        String topicResults = "Results";

        java.util.Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> lines = builder.stream(topicSales);
        //lines.foreach((key, value) -> System.out.println(key + " => " + value));


        KTable<String, Float> revenue =  lines.mapValues(v->transform(v)).groupByKey(Grouped.with(Serdes.String(),Serdes.Float())).reduce((v1,v2)->v1+v2);


        revenue.toStream().mapValues((k,v)->""+k +" -> "+v).to(topicResults, Produced.with(Serdes.String(),Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }

    /*
    {"schema":{"type":"struct","fields":[{"type":"double","optional":false,"field":"revenue"},{"type":"double","optional":false,"field":"expenses"},{"type":"double","optional":false,"field":"profit"}],"optional":false,"name":"total data"},"payload":{"revenue":988500.0, "expenses":731430.0,"profit":257070.0}}
     */
/*    public String revenuesJson(String value){

        JSONObject json = new JSONObject();

    }*/
    // Vai receber o value, pegar no units e price e multiplicar um pelo outro
    private static float transform(String v) {
        JSONObject json = new JSONObject(v);
        System.out.println("Preço: "+json.getFloat("price"));
        System.out.println("Unidades: "+json.getInt("units"));
        System.out.println("Total: "+json.getFloat("price")*json.getInt("units"));
        return json.getFloat("price")*json.getInt("units");
    }
}
