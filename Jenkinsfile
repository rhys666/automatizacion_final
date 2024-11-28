pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio
                git branch: 'main', url: 'https://github.com/rhys666/automatizacion_final'
            }
        }
        
        stage('Build') {
            steps {
                // Ejecutar Maven para construir el proyecto
                sh 'mvn clean install'
            }
        }
        
        stage('Test') {
            steps {
                // Ejecutar pruebas con Maven
                sh 'mvn test'
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