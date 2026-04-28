pipeline {
    agent any

    stages {
        stage('Clonar projeto') {
            steps {
                checkout scm
            }
        }

        stage('Preparar Maven Wrapper') {
            steps {
                sh 'chmod +x mvnw'
            }
        }

        stage('Build e testes') {
            steps {
                sh './mvnw clean package'
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