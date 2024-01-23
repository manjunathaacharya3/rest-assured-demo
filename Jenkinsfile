pipeline {

    agent any
    
    parameters {
    choice(choices:[ "stage","test"], description:"Execution enviroment",name:"excEnv")
    string(defaultValue:"@Regression", description:"Specify the tags",name:"runTags")
    booleanParam(defaultValue:false, description:"Sonar Analysis",name:"sonar")
    booleanParam(defaultValue:true, description:"Send Execution Report",name:"triggerReport")
    }
    
	triggers {
	    pollSCM(env.BRANCH_NAME='master' ? '*/2 * * * *' : '')
	}

    stages {
        stage("Build") {
	        steps {	
		          bat "mvn clean install -DskipTests"		        
	        }       
        }
        stage("Test") {      
              steps {   
            	  bat "mvn test -Dcucumber.filter.tags=${params.runTags}"          	    
       		}
        }
    }
    
    post {
        success {
            emailext subject: 'Execution is successful',
                      body: 'Test Execution is succesful ! Env -${params.excEnv}',
                      to: 'manjunathaacharya3@gmail.com',
                      attachLog: true
        }

        failure {
            emailext subject: 'Execution is incomplete',
                      body: 'Test Execution is failed ! Env - ${params.excEnv}',
                      to: 'manjunathaacharya3@gmail.com',
                      attachLog: true
        }
    }
}