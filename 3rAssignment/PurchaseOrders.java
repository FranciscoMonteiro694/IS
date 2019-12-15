package Kafka;//import util.properties packages

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.json.JSONObject;

import java.util.*;

//import simple producer packages
//import KafkaProducer packages
//import ProducerRecord packages

//Create java class named “Kafka.SimpleProducer”
// Esta classe vai buscar os itens ao DBInfo topic e vai meter purchases no Purchases Topic
// Cada purchase vai ter um preço e número de unidades
public class PurchaseOrders {

    public static void main(String[] args) throws Exception {

        // Onde vai buscar os itens e países
        String topicDBInfo = "DBInfo";

        // Onde vai meter as Purchases (Preço e número de unidades)
        String topicPurchases = "Purchases";
        // create instance for properties to access producer configs
        Properties props = new Properties();

        // Vindos do consumer (Inicio)
        props.put("group.id", "Kafka.PurchaseOrders");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,Purchase.class.getName());

        // FIM


        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        // Criar um consumer
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // Subscrever o tópico
        consumer.subscribe(Collections.singletonList(topicDBInfo));

        // Tentativa

        try {
            List<Integer> itens = new ArrayList<Integer>();
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    JSONObject json = new JSONObject(record.value());
                    // Se for um item
                    if (json.getJSONObject("payload").has("item_id")) {
                        // Se a lista de indices de itens ainda não tiver o indice, adicionar
                        if (!itens.contains(json.getJSONObject("payload").getInt("item_id"))) {
                            itens.add(json.getJSONObject("payload").getInt("item_id"));
                        }
                    }
                }
                if(itens.size()!=0 ) {
                    // Escolhe um id de um item aleatoriamente
                    Random rnd = new Random();
                    int indiceItemRandom = rnd.nextInt(itens.size());
                    int idItemRandom = itens.get(indiceItemRandom);

                    // Cria uma purchase aleatoriamente
                    Purchase p = new Purchase(idItemRandom, numeroRandomINT(1, 5), numeroRandomINT(1, 5));
                        /*System.out.println("ID item Random: "+p.getItem_id());
                        System.out.println("Teste preço random: "+p.getPrice());
                        System.out.println("Teste unidades random: "+p.getUnits());*/

                    // Converter de Purchase para json e de json para string
                    JSONObject jsonObject = new JSONObject(p);
                    String myJson = jsonObject.toString();
                    //System.out.println("Json novo:"+myJson);
                    // Adiciona ao tópico Purchases
                    // Criar um producer
                    Producer<String, String> producer = new KafkaProducer<>(props);
                    producer.send(new ProducerRecord<String, String>(topicPurchases, Integer.toString(p.getItem_id()), myJson));
                    System.out.println("Purchase adicionada!");
                    producer.close();
                    Thread.sleep(5000);
                }

            }
        } finally {
            consumer.close();
        }

        // Fim tentativa

        // Blah blah blah
        /*Producer<String, Long> producer = new KafkaProducer<>(props);

        for(int i = 0; i < 1000; i++)
            producer.send(new ProducerRecord<String, Long>(topicName, Integer.toString(i), (long) i));

        System.out.println("Message sent successfully to topic " + topicName);
        producer.close();
*/
    }

    // Faz o numero random entre dois numeros
    public static int numeroRandomINT(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        return result;
    }
    public static float numeroRandomFloat(float low, float high) {
        Random r = new Random();
        float result = low + r.nextFloat() * (high - low);
        return result;
    }
}