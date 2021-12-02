pipeline {
    agent any
    tools {
    	maven 'MAVEN'
    }
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
                sh 'docker build -t sanu07/employee-service'
            }
        }
        stage('Push to Dockerhub') {
            steps {
                withCredentials([usernamePassword(credentialsId: '397c47e1-4e41-4b3b-855f-efcbb8db2ea2', passwordVariable: 'pwd', usernameVariable: 'username')]) {
    				sh 'docker login -u ${username}' -p ${pwd}
				}
				sh 'docker push sanu07/employee-service'
            }
        }
    }
}