pipeline {
    agent any

    stages {
        stage('Preparar Maven Wrapper') {
            steps {
                sh 'chmod +x mvnw'
            }
        }

        stage('Build e testes') {
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
                        -Dsonar.projectName=supermercado
                    '''
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
