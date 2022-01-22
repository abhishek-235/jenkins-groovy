pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build Step Initiated'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing completed'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deployment completed'
            }
        }
    }
}

