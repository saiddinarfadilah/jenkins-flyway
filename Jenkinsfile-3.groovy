pipeline {
    agent any

    environemnt = {
        DB_CREDENTIALS = credentials('db-credentials-postgres')
    }

    stages {

        stage('FLYWAY VERSION') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway version"
                }
            }
        }
        stage('FLYWAY MIGRATE') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$DB_CREDENTIALS_USR -password=$DB_CREDENTIALS_PSW migrate"
                }
            }
        }

        stage('FLYWAY VALIDATE') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$DB_CREDENTIALS_USR -password=$DB_CREDENTIALS_PSW validate"
                }
            }
        }

        stage('FLYWAY INFO') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$DB_CREDENTIALS_USR -password=$DB_CREDENTIALS_PSW info"
                }
            }
        }
    }
}