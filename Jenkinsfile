pipeline {
	environment { 
	        registry = "sanu07/employee-service" 
	        registryCredential = 'dockerhub_id' 
	        dockerImage = '' 
	    }
    agent any
    stages {
        stage('Clean and Package') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn test -P unit-test'
            }
        }
        stage('Integration Test') {
            steps {
                sh 'mvn verify -P integration-test'
            }
        }
        stage('Build Docker Image') {
            steps {
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            }
        }
        stage('Push to Dockerhub') {
            steps {
                script { 
                    docker.withRegistry( '', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        }
    }
}