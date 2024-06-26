from airflow import DAG
from airflow.operators.bash import BashOperator
from datetime import datetime, timedelta

default_args = {
    'owner': 'airflow',
    'depends_on_past': False,
    'start_date': datetime(2024, 1, 1),
    'email_on_failure': False,
    'email_on_retry': False,
    'retries': 0,
    'retry_delay': timedelta(minutes=5),
}

dag = DAG(
    'ev_vehicles',
    description='A simple DAG to process EV vehicles data',
    schedule_interval=timedelta(days=1),
    max_active_runs=1,
    default_args=default_args
)

raw_to_silver = BashOperator(
    task_id='raw_to_silver',
    bash_command='spark-shell -i /opt/airflow/dags/raw_to_silver.scala',
    dag=dag,
)

silver_to_gold = BashOperator(
    task_id='silver_to_gold',
    bash_command='spark-shell -i /opt/airflow/dags/silver_to_gold.scala',
    dag=dag,
)

raw_to_silver >> silver_to_gold
