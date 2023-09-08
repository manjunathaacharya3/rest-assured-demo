pipeline {

    agent any

    triggers {
	    pollSCM('*/2 * * * *')
	}

    stages {
        stage("Build") {
	        steps {		       
		           bat "mvn clean install -DskipTests"  		        		                
	        }       
        }
	    
        stage("Test") {
            steps {           
            	   bat "mvn test"           	    
            }
        }
    }
}
