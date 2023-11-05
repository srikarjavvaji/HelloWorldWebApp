node {
    def mavenHome = '/opt/apache-maven-3.8.8' // Specify the correct path to your Maven installation

    try {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/srikarjavvaji/HelloWorldWebApp.git']]])
        }

        stage('Build') {
            // Specify the full path to the Maven executable
            sh "${mavenHome}/bin/mvn clean package"
        }

        stage('Run') {
            // Start the Jetty server
            def server = new ProcessBuilder('java', '-jar', 'target/HelloWorldJavaApp-jar-with-dependencies.jar')
            server.redirectErrorStream(true)
            def process = server.start()

            // Wait for the process to complete
            def exitCode = process.waitFor()

            if (exitCode != 0) {
                error "Failed to start the application"
            }
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error("An error occurred: ${e.message}")
    } finally {
        stage('Cleanup') {
            // Stop the Jetty server if it's running
            sh 'pkill -f HelloWorldJavaApp-jar-with-dependencies.jar'
        }
    }
}
