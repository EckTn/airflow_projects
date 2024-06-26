
echo "Installing and Upgrading Python:"
sudo apt-get update
sudo apt-get install -y python3-pip python3-dev libpq-dev
python3 -m pip install --upgrade pip

echo "Installing and configuring PostgreQSQL"
postgresql postgresql-contrib docker.io docker-compose
sudo -u postgres psql
# enter the SQL code from DB-Scripts.sql to set up the PostGre user and database

mkdir airflow
cd airflow