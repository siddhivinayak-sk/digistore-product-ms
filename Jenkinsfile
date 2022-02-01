pipeline {
    agent {label 'Ubuntu'}
    stages {

        stage('Clean') {
            steps {
                echo "Clean Started"
                sh(/mvn -file pom.xml clean/)
                echo "Clean End"
            }
        }

        stage('Compile') {
            steps {
                echo "Code Compilation Started"
                sh(/mvn -file pom.xml compile/)
                echo "Code Compilation End"
            }
        }
        
        stage('Test') {
            steps {
                echo "Test Started"
                sh(/mvn -file pom.xml org.jacoco:jacoco-maven-plugin:prepare-agent test -Dmaven.test.failure.ignore=true/)
                echo "Test End"
            }
        }

        stage('Jacoco') {
            steps {
                echo "Jacoco Started"
                jacoco classPattern: '**/target/classes', exclusionPattern: '**/*Test*.class', inclusionPattern: '', sourcePattern: '**/src/main'
                echo "Jacoco End"
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo "SonarQube Started"
                withSonarQubeEnv('localSonarQube') {
                    sh(/mvn -file pom.xml  sonar:sonar  -Dmaven.test.skip=true/)
                }
                echo "SonarQube End"
            }
        }

        stage('Result') {
            steps {
                echo "Result Started"
                junit '**/target/surefire-reports/TEST-*.xml'
                echo "Result End"
            }
        }

        stage('Artifact Publish') {
            steps {
                echo "Artifact Publish to Nexus Started"
                sh(/mvn -file pom.xml  deploy -Dmaven.test.skip=true/)
                echo "Artifact Publish to Nexus End"
            }
        }
        
        stage('Build Image') {
            steps {
                echo "Build Image Started"
                sh(/mvn -file pom.xml package -Dmaven.test.skip=true/)
                sh(/docker build --build-arg VER=0.0.1 -f dockerfile -t digistore\/digistore-product-ms:latest ./)
                echo "Build Image End"
            }
        }
        
        stage('Scan Image') {
            steps {
                echo "Scan Image Started"
                sh(/docker scan --file dockerfile --json digistore\/digistore-product-ms:latest > digistore-product-ms_latest.json/)
                echo "Scan Image End"
            }
        }

        stage('Tag Image') {
            steps {
                echo "Tag Image Started"
                sh(/docker tag digistore\/digistore-product-ms:latest hub.docker.local:5000\/digistore\/digistore-product-ms:latest/)
                echo "Tag Image End"
            }
        }

        stage('Push Image') {
            steps {
                echo "Push Image Started"
                sh(/docker push hub.docker.local:5000\/digistore\/digistore-product-ms:latest/)
                echo "Push Image End"
            }
        }

    }
}
