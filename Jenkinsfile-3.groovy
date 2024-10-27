pipeline {
    agent any
    environment {
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
                    withCredentials([usernamePassword(credentialsId: "${DB_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$USERNAME -password=$PASSWORD migrate"
                    }
                }
            }
        }



        stage('FLYWAY VALIDATE') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DB_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$USERNAME -password=$PASSWORD validate"
                    }
                }
            }
        }

        stage('FLYWAY INFO') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "${DB_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        bat "${env.FLYWAY_HOME}/flyway -configFiles=./conf/flyway3.conf -user=$USERNAME -password=$PASSWORD info"
                    }
                }
            }
        }
    }
}