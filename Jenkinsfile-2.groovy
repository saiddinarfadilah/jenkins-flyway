pipeline {
    agent any
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
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway.conf migrate"
                }
            }
        }

        stage('FLYWAY VALIDATE') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway.conf validate"
                }
            }
        }

        stage('FLYWAY INFO') {
            steps {
                script {
                    bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway.conf info"
                }
            }
        }
    }
}