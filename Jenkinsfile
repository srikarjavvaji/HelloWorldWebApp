node {
    try {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfig: [[url: 'https://github.com/srikarjavvaji/HelloWorldWebApp.git']]])
        }

        stage('Build') {
            // Specify the complete path to the Maven executable
            sh '/opt/apache-maven-3.8.8/bin/mvn clean install'
        }

        stage('Run Jetty Server') {
            // Specify the complete path to the Maven executable
            sh '/opt/apache-maven-3.8.8/bin/mvn jetty:run'
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error("An error occurred: ${e.message}")
    } finally {
        archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
    }
}
