node {
    def mavenHome = '/opt/apache-maven-3.8.8' // Specify the correct path to your Maven installation

    try {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/srikarjavvaji/HelloWorldWebApp.git']])
        }

        stage('Build') {
            sh "${mavenHome}/bin/mvn clean package"
        }

        stage('Run') {
            echo "Running the application..."
            def server = new ProcessBuilder('java', '-jar', 'target/HelloWorldJavaApp-jar-with-dependencies.jar')
            server.redirectErrorStream(true)
            def process = server.start()

            echo "Waiting for the process to complete..."
            def exitCode = process.waitFor()

            if (exitCode != 0) {
                error "Failed to start the application (Exit Code: $exitCode)"
            }
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        error("An error occurred: ${e.message}")
    } finally {
        stage('Cleanup') {
            sh 'pkill -f HelloWorldJavaApp-jar-with-dependencies.jar'
        }
    }
}
