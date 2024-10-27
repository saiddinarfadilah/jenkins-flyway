pipeline {
    agent any
    stages {
        stage('FLYWAY VERSION') {
            steps {
                script {
                    bat 'D:/Local/flyway/flyway-10.20.1/flyway version'
                }
            }
        }
    }
}