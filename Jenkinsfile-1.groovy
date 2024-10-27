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
    }
}