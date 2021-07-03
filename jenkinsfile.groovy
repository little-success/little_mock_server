stage('pull source code') {
    node('master'){
        git([url: 'https://github.com/little-success/little_mock_server.git', branch: 'master'])
    }
}


stage('clean docker environment') {
    node('master'){
        try{
            sh 'docker stop my_mock_server'
        }catch(exc){
            echo 'my_mock_server container is not running!'
        }

        try{
            sh 'docker rm my_mock_server'
        }catch(exc){
            echo 'my_mock_server container does not exist!'
        }
        try{
            sh 'docker rmi mock:v1.0'
        }catch(exc){
            echo 'mock:v1.0 image does not exist!'
        }
    }
}

stage('make new docker image') {
    node('master'){
        try{
            sh 'docker build -t mock:v1.0 .'
        }catch(exc){
            echo 'Make mock:v1.0 docker image failed, please check the environment!'
        }
    }
}

stage('start docker container') {
    node('master'){
        try{
            sh 'docker run --name my_mock_server -d -p 8809:8809 mock:v1.0'
        }catch(exc){
            echo 'Start docker image failed, please check the environment!'
        }
    }
}
