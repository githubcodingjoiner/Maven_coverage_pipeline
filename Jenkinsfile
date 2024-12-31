pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & test') {
            steps {
                bat '''
                echo "Running JUnit and Selenium tests using Maven..."
                mvn clean verify
                '''
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token') // Accessing the SonarQube token
            }
            steps {
                bat '''
                mvn clean verify sonar:sonar ^
                -Dsonar.projectKey=Maven_coverage ^
                -Dsonar.sources=. ^
                -Dsonar.projectName='Maven_coverage' ^
                -Dsonar.host.url=http://localhost:9000 ^
                -Dsonar.token=%SONAR_TOKEN%
                '''
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
        always {
            echo 'This runs regardless of the result.'
        }
    }
}
