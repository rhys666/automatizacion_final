pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio
                git branch: 'master', url: 'https://github.com/rhys666/automatizacion_final'
            }
        }
        
        stage('Build') {
            steps {
                // Ejecutar Maven para construir el proyecto
                bat 'mvn clean install'
            }
        }
        
        stage('Test') {
            steps {
                // Ejecutar pruebas con Maven
                bat 'mvn test'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                // Guardar artefactos generados (como archivos .jar o .war)
                archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado correctamente.'
        }
        failure {
            echo 'Pipeline falló. Revisar los logs.'
        }
    }
}