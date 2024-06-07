import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

val spark = SparkSession.builder.appName("RawToSilver").getOrCreate()
val filePath = "/home/et124/csv_files/vehicles_dataset.csv"
val vehiclesDF = spark.read.format("csv").option("header", "true").load(filePath)

// Handle missing values
val vehiclesCleanedDF = vehiclesDF
  .withColumn("description", coalesce(col("description"), lit("")))
  .withColumn("price", coalesce(col("price").cast("double"), avg("price").over()))
  .withColumn("cylinders", coalesce(col("cylinders").cast("int"), lit(4))) 
  .withColumn("mileage", coalesce(col("mileage").cast("double"), avg("mileage").over()))
  .withColumn("interior_color", coalesce(col("interior_color"), lit("Black")))
  
// Remove duplicates
val vehiclesDistinctDF = vehiclesCleanedDF.dropDuplicates()


val outputFilePath = "/home/et124/csv_files/silver_vehicles_dataset.csv"
vehiclesDistinctDF.write.mode("overwrite").format("csv").option("header", "true").save(outputFilePath)

spark.stop()
