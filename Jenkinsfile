pipeline {
    agent any

    stages {
        stage('Preparar Maven Wrapper') {
            steps {
                sh 'chmod +x mvnw'
            }
        }

        stage('Build, testes e cobertura') {
            steps {
                sh './mvnw clean verify'
            }
        }

        stage('Análise SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        ./mvnw sonar:sonar \
                        -Dsonar.projectKey=supermercado \
                        -Dsonar.projectName=supermercado \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    '''
                }
            }
        }

        stage('Verificar Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executada com sucesso!'
        }

        failure {
            echo 'A pipeline falhou. Verifique os logs do Jenkins.'
        }
    }
}