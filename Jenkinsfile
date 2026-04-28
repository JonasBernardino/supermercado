pipeline {
    agent any

    stages {
        stage('Clonar projeto') {
            steps {
                checkout scm
            }
        }

        stage('Compilar aplicação') {
            steps {
                sh './mvnw clean compile'
            }
        }

        stage('Executar testes') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Gerar arquivo JAR') {
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