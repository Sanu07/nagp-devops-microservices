pipeline {
	environment { 
	        registry = "sanu07/employee-service" 
	        registryCredential = '397c47e1-4e41-4b3b-855f-efcbb8db2ea2' 
	        dockerImage = '' 
	    }
    agent any
    stages {
        stage('Checkout') {
            steps {
                  checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '9c887d11-0729-4925-b739-bbfe0d3ef657', url: 'https://github.com/Sanu07/nagp-devops-microservices.git']]])
            }
        }
            
        stage('Clean and Package') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Unit Test') {
            steps {
                bat 'mvn test -P unit-test'
            }
        }
        stage('Integration Test') {
            steps {
                bat 'mvn verify -P integration-test'
            }
        }
        stage('Build Docker Image') {
            agent {
                label 'docker-agent'
            }
            steps {
                script { 
                   dockerImage = docker.build registry
                }
            }
        }
        stage('Push to Dockerhub') {
            agent {
                label 'docker-agent'
            }
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