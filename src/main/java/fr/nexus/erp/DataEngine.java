package fr.nexus.erp;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class DataEngine {
    public void runPipeline(){
        SparkSession spark = SparkSession.builder()
                .appName("Nexus_ERP")
                .config("spark.master", "local[*]")
                .getOrCreate();

        String url = "jdbc:mysql://localhost:3306/nexus_erp";
        String user = "admin";
        String password = "Adm1n!";

        Properties connectionProp = new Properties();
        connectionProp.put("user", user);
        connectionProp.put("password", password);
        connectionProp.put("driver", "com.mysql.cj.jdbc.Driver");

        System.out.println("============================================");
        System.out.println("CONNEXION DB NEXUS_ERP");
        System.out.println("============================================");

        // Lecture de la table server_logs
        Dataset<Row> dfMySQL = spark.read().jdbc(url, "server_logs", connectionProp);

        /* TRAITEMENT */
        System.out.println("NETTOYAGE ET TRI DES DONNÉES...");

        Dataset<Row> dfProcessed = dfMySQL
                .select("server_name", "region", "status") // Sélection
                .filter(dfMySQL.col("region").isNotNull())  // Suppression des NULL
                .orderBy("region", "server_name");          // Tri

        dfProcessed.show();

        /* EXPORTATION */
        System.out.println("============================================");
        System.out.println("EXPORTATION VERS ARCHIVES_LOGS_SERVEURS");
        System.out.println("============================================");

        dfProcessed.write()
                .mode(SaveMode.Overwrite)
                .partitionBy("region")
                .json("archives_logs_serveurs");

        System.out.println("Fin du traitement Spark.");
        spark.stop();

    }
}
