pipeline {
    agent any

//     environment {
//         MAVEN_HOME = '/usr/share/maven'
//     }

    environment {
        MAVEN_HOME = '/usr/share/maven'
        MAVEN_OPTS = '-Dmaven.multiModuleProjectDirectory=$WORKSPACE'
    }

    tools {
        maven 'Maven'
        jdk 'Java-11'
    }

    stages {

        stage('Find Maven') {
            steps {
                sh 'echo $PATH'
                sh 'which mvn || echo "Maven not found in PATH"'
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn --version'
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

//         stage('Deploy to Artifactory') {
//             steps {
//
//             //sh 'mvn clean deploy -f pom.xml'
//
// //                 script {
// //                     def artifactory = Artifactory.server('Artifactory')
// //                     def buildInfo = Artifactory.newBuildInfo()
// //                     def rtMaven = Artifactory.newMavenBuild()
// //                     rtMaven.tool = 'Maven'
// //                     rtMaven.deployer releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: artifactory
// //                     rtMaven.resolver server: artifactory, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
// //                     buildInfo = rtMaven.run pom: 'pom.xml', goals: 'deploy'
// //                     artifactory.publishBuildInfo buildInfo
// //                 }
//
// //             script {
// //                 def artifactory = Artifactory.server('Artifactory')
// //                 def rtMaven = Artifactory.newMavenBuild()
// //                 rtMaven.tool = 'Maven'
// //                 rtMaven.deployer releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: artifactory
// //                 rtMaven.run pom: 'pom.xml', goals: 'deploy'
// //             }
//
//             }
//         }
//
//         stage('Archive Artifacts') {
//             steps {
//                 archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
//             }
//         }
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
