pipeline {
    agent any
    tools {
        maven 'sonarmaven' 
    }
    
    environment {
        SONARQUBE_SERVER = 'sonarqube'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat '''
                echo "Running tests with Jacoco coverage using Maven..."
                mvn clean verify -Pcoverage
                '''
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token') // Accessing the SonarQube token
            }
            steps {
                bat '''
                mvn sonar:sonar ^ 
                -Dsonar.projectKey=Maven_coverage ^ 
                -Dsonar.sources=. ^ 
                -Dsonar.projectName='Maven_coverage' ^ 
                -Dsonar.host.url=http://localhost:9000 ^ 
                -Dsonar.token=%SONAR_TOKEN% ^ 
                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
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
