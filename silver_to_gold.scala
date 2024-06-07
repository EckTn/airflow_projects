import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

val spark = SparkSession.builder.appName("SilverToGold").getOrCreate()

val filePath = "/home/et124/csv_files/silver_vehicles_dataset.csv"
val vehiclesDF = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load(filePath)

// Capitalize the first letter of each word in the column names
val renamedDF = vehiclesDF.columns.foldLeft(vehiclesDF) { (df, colName) =>
  df.withColumnRenamed(colName, colName.split("_").map(_.capitalize).mkString(""))
}

// Convert cylinders and doors columns to int
val typedDF = renamedDF
  .withColumn("Cylinders", col("Cylinders").cast(IntegerType))
  .withColumn("Doors", col("Doors").cast(IntegerType))

// Rename price column to Price(USD)
val finalDF = typedDF.withColumnRenamed("Price", "Price(USD)")


val outputFilePath = "/home/et124/csv_files/gold_vehicles_dataset.csv"
finalDF.write.mode("overwrite").format("csv").option("header", "true").save(outputFilePath)

spark.stop()
