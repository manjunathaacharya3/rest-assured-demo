pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
			   git 'https://github.com/manjunathaacharya3/rest-assured-demo.git'
			   
               bat "mvn clean install -DskipTests"
            }
        }
		stage('Test') {
            steps {
               bat "mvn test"
            }
        }
    }
}