pipeline {
    agent any

    tools {
        maven 'Maven-3.6.3'
        jdk 'Java-11'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Artifactory') {
            steps {
                script {
                    def artifactory = Artifactory.server('Artifactory')
                    def buildInfo = Artifactory.newBuildInfo()
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.tool = 'Maven-3.6.3'
                    rtMaven.deployer releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: artifactory
                    rtMaven.resolver server: artifactory, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    buildInfo = rtMaven.run pom: 'pom.xml', goals: 'deploy'
                    artifactory.publishBuildInfo buildInfo
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Build was successful.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
