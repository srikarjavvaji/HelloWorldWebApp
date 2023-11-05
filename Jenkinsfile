node {
    try {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfig: [[url: 'https://github.com/srikarjavvaji/HelloWorldWebApp.git']]])
        }

        stage('Build') {
            // You can access the PATH environment variable like this:
            def mavenPath = env.PATH

            // Use the 'sh' step to run Maven with the correct PATH:
            sh "${mavenPath}/mvn clean install"
        }

        stage('Run Jetty Server') {
            // Use the 'sh' step to run Maven with the correct PATH:
            sh "${mavenPath}/mvn jetty:run"
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error("An error occurred: ${e.message}")
    } finally {
        archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
    }
}
