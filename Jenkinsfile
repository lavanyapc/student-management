pipeline {
    agent any

    // These names must match the tool names you configure under
    // "Manage Jenkins > Tools" (see the setup guide, Step 6).
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME     = 'student-management'
        CONTAINER_NAME = 'student-management-app'
        APP_PORT       = '8080'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code from Git...'
                checkout scm
            }
        }

        stage('Build & Test (Maven)') {
            steps {
                echo 'Compiling application and running unit tests...'
                sh 'mvn -B clean package'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image from the packaged JAR...'
                sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Deploy Container') {
            steps {
                echo 'Stopping any previous container and starting a new one...'
                sh """
                    docker stop ${CONTAINER_NAME} || true
                    docker rm ${CONTAINER_NAME} || true
                    docker run -d --name ${CONTAINER_NAME} -p ${APP_PORT}:8080 ${IMAGE_NAME}:latest
                """
            }
        }

        stage('Verify Deployment') {
            steps {
                echo 'Waiting for app to start, then checking health endpoint...'
                sh '''
                    sleep 8
                    curl -f http://localhost:${APP_PORT}/api/students/health
                '''
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully. App is running on port ${APP_PORT}."
        }
        failure {
            echo 'Pipeline failed. Check the stage logs above for details.'
        }
        always {
            echo "Build #${BUILD_NUMBER} finished with status: ${currentBuild.currentResult}"
        }
    }
}
