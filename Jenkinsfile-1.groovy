pipeline {
    agent any
    stages {
        stage('FLYWAY VERSION') {
            steps {
                script {
                    bat 'flyway version'
                }
            }
        }
    }
}