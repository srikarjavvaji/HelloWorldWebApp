node {
    try {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/srikarjavvaji/HelloWorldWebApp.git']]])
        }

        stage('Build') {
            sh 'mvn clean install'
        }

        stage('Run Jetty Server') {
            sh 'mvn jetty:run'
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error("An error occurred: ${e.message}")
    } finally {
        archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
    }
}
