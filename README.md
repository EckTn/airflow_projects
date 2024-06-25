# airflow_projects
# EV Vehicles Data Pipeline

This project sets up an Apache Airflow data pipeline to process Electric Vehicle (EV) data using Spark. The pipeline is ran on a GCP Compute Engine instance using Docker containers.
The pipeline reads raw EV data from a csv file, processes it to a silver level, and finally transforms it into a gold dataset ready for business use.

### Key Features

- **Apache Airflow**: Used for orchestrating the data pipeline.
- **Apache Spark**: Used for data processing.
- **Compute Engine (GCP)**: Compute Engine vm instance to host the environment
- **Docker**: Containerized environment for consistency and portability.
- **PostgreSQL**: Used as the Airflow metadata database.

### Step-by-Step Setup

#### 1. Create Compute Engine Instance

1. Create a new instance on GCP with Ubuntu.
2. SSH into the instance.

#### 2. Install Docker and Docker Compose

