def createVirtualEnv(env_name) {
    echo 'Creating virtual environment $env_name'
    sh "virtualenv -p python3.8 $env_name"
}
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Build Step Initiated'
                script {createVirtualEnv('v_env')}
                sh '''
                    source v_env/bin/activate
                    
                    if [[ -f requirements.txt ]]; then
                        echo 'Installing requirements...'
                        pip install -r requirements.txt
                    fi
                    deactivate
                '''
            }
        }
        stage("Pylint") {
            steps {
                parallel (
                "Pylint": {
                    echo 'Checking linting errors...'
                },
                "AnotherJob": {
                    echo "running another job in parallel"
                }
                )
            }
        }
        stage('Test') {
            steps {
                parallel (
                "Integration Test": {
                    sh """
                        source v_env/bin/activate
                        pytest tests/example_tests.py -m cleanup
                    """
                    echo 'Integration Testing completed...'    
                },
                "e2e Test": {
                    sh """
                        source v_env/bin/activate
                        pytest tests/example_tests.py -m e2e_test
                    """
                    echo 'E2E Testing completed...'    
                }
                
                )
            }
        }
        stage('Clean') {
            steps {
                sh """
                    source v_env/bin/activate
                    pytest tests/example_tests.py -m cleanup
                    deactivate
                """
                echo 'Cleanup completed...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deployment completed'
            }
        }
    }
}