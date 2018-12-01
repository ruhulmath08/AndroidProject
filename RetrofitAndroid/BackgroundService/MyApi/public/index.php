<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';
require '../includes/DbOperations.php';

//$app = new \Slim\App;
$app = new \Slim\App([
    'settings' => [
        'displayErrorDetails' => true
    ]
]);

/*
    endpoint: createuser
    parameters: email, password, name, school
    method: POST
*/

//Create post call for create user
$app->post('/createuser', function(Request $request, Response $response){
    if(!haveEmptyParameters(array('email', 'password', 'name', 'school'), $request, $response)){
        $request_data = $request->getParsedBody();

        $email = $request_data['email'];
        $password = $request_data['password'];
        $name = $request_data['name'];
        $school = $request_data['school'];

        $hash_password = password_hash($password, PASSWORD_DEFAULT);

        $db = new DbOperations;

        $result = $db->createUser($email, $hash_password, $name, $school);

        if($result == USER_CREATED){
            $message = array();
            $message['error'] = false;
            $message['message'] = 'User created successfully';

            $response->write(json_encode($message));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(201);
        }
        else if ($result == USER_FAILUR) {
            $message = array();
            $message['error'] = true;
            $message['message'] = 'Some error occurred';

            $response->write(json_encode($message));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
        }
        else if($result ==USER_EXISTS){
            $message = array();
            $message['error'] = true;
            $message['message'] = 'User already exixts';

            $response->write(json_encode($message));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(422);
        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

//create POST call for user login
$app->post('/userlogin', function(Request $request, Response $response){
    if(!haveEmptyParameters(array('email', 'password'), $request, $response)){
        $request_data = $request->getParsedBody();

        $email = $request_data['email'];
        $password = $request_data['password'];

        $db = new DbOperations;
        
        $result = $db->userLogin($email, $password);

        if($result == USER_AUTHENTICATED){
            
            $user = $db->getUserByEmail($email);
            $response_data = array();

            $response_data['error'] = false;
            $response_data['message'] = 'Login Successful';
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
        else if($result == USER_NOT_FOUND){

            $response_data = array();

            $response_data['error'] = true;
            $response_data['message'] = 'User not exist';

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
        else if($result == PASSWORD_DO_NOT_MATCH){

            $response_data = array();

            $response_data['error'] = true;
            $response_data['message'] = 'INVALID credential';

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

//create GET call for user login
$app->get('/allusers', function(Request $request, Response $response){
    
    $db = new DbOperations;

    $users = $db->getAllUsers();

    $response_data = array();

    $response_data['error'] = false;
    $response_data['users'] = $users;

    $response->write(json_encode($response_data));

    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

//create PUT request for update operation
$app->put('/updateuser/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];
    
    if(!haveEmptyParameters(array('email', 'name', 'school', 'id'), $request, $response)){

        $request_data = $request->getParsedBody();

        $email = $request_data['email'];
        $name = $request_data['name'];
        $school = $request_data['school'];
        $id = $request_data['id'];

        $db = new DbOperations;

        if($db->updateUser($email, $name, $school, $id)){
            $response_data = array();
            $response_data['error'] = false;
            $response_data['message'] = 'User Update Successfully';
            //face new data
            $user = $db->getUserByEmail($email);
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
        else{
            $response_data = array();
            $response_data['error'] = true;
            $response_data['message'] = 'User info not update, please try agatn later';
            //face new data
            $user = $db->getUserByEmail($email);
            $response_data['user'] = $user;

            $response->write(json_encode($response_data));

            return $response
                ->withHeader('Content-type', 'application/json')
                ->withStatus(200);
        }
    }
    return $response
    ->withHeader('Content-type', 'application/json')
    ->withStatus(200);
});

//create PUT request for update passuord
$app->put('/updatepassword/{id}', function(Request $request, Response $response, array $args){
    $id = $args['id'];

    if(!haveEmptyParameters(array('currentpassword', 'newpassword', 'email'), $request, $response)){

        $request_data = $request->getParsedBody();

        $currentpassword = $request_data['currentpassword'];
        $newpassword = $request_data['newpassword'];
        $email = $request_data['email'];

        $db = new DbOperations;
    }
    return $response
        ->withHeader('Content-type', 'application/json')
        ->withStatus(422);
});

//validation for each call
function haveEmptyParameters($required_params, $request, $response){
    $error = false;
    $error_params = '';
    $request_params = $request->getParsedBody();

    foreach ($required_params as $param) {
        if(!isset($request_params[$param]) || strlen($request_params[$param]) <= 0){
            $error = true;
            $error_params .= $param . ', ';
        }
    }

    if ($error) {
        $error_detail = array();
        $error_detail['error'] = true;
        $error_detail['message'] = 'Required parameters ' . substr($error_params, 0, -2) . ' are missing or empty';
        $response->write(json_encode($error_detail));
    }

    return $error;
}

//run the app
$app->run();