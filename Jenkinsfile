pipeline {
	agent any
	
	tools {
		jdk "JDK 11"   /* may need to be adapted */
		maven "Maven 3.6.3"   /* may need to be adapted */
	}
    
    parameters {
        booleanParam(
			name: "Extended",
			description: "run extended tests",
			defaultValue: false
		)
    }
    
	stages {
		stage("Build") {
			steps {				
				sh "mvn clean package"
			}
		}
		
		stage("Test") {
			when {
				expression {
					return params.Extended
				}
			}
			steps {
				sh "mvn verify -DskipUnitTests"
			}
		}

		stage("Analyze") {
			steps {
				script {
					def sonarQubeScannerHome = tool "SonarQube Scanner 4.6"   /* may need to be adapted */
					withSonarQubeEnv("SonarQube 8.9") {   /* may need to be adapted */
						sh "${sonarQubeScannerHome}/bin/sonar-scanner"
					}
				}
			}
		}
	}
	
	post {
		always {
			archiveArtifacts artifacts: '**/*.jar', fingerprint: true
			junit allowEmptyResults: true, testResults: "target/surefire-reports/*.xml,target/failsafe-reports/*.xml"
			
			jacoco()
		}
	}
}
