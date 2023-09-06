pipeline {

    agent any

    stages {
        stage("Build") {
	        steps {
		        dir("rest-assured-demo") {
		           bat "mvn clean install -DskipTests"  
		        }		                
	        }       
        }
        stage("Test") {
            steps {
            	dir("rest-assured-demo") {
            	   bat "mvn test"
            	}       
            }
        }
    }
}