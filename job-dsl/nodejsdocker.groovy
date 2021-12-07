job('NodeJS Docker DSL') {
    scm {
        git('git://github.com/aashiqAhamedSP/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('aashiqAhamedSP')
            node / gitConfigEmail('aashiqahamedsp@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('aashiqdevops/nodejshelloworld')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
