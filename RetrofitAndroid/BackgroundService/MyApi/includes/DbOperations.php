<?php

class DbOperations{

    private $con;

    function __construct(){
        require_once dirname(__FILE__) . '/DbConnect.php';

        $db = new DbConnect;

        $this->con = $db->connect();
    }

    //create user
    public function createUser($email, $password, $name, $school){
        if(!$this->isEmailExist($email)){
            $stmt = $this->con->prepare("INSERT INTO users(email, password, name, school) VALUES (?, ?, ?, ?)");
            $stmt->bind_param("ssss", $email, $password, $name, $school);
            if($stmt->execute()){
                return USER_CREATED;
            }else{
                return UUSER_FAILUR;
            }
        }
        return USER_EXISTS;
    }

    //user login
    public function userLogin($email, $password){
        if($this->isEmailExist($email)){

            $hashed_password = $this->getUserPasswordByEmail($email);
            if(password_verify($password, $hashed_password)){
                return USER_AUTHENTICATED;
            }
            else{
                return PASSWORD_DO_NOT_MATCH;
            }
        }
        else{
            return USER_NOT_FOUND;
        }
    }

    //update operation
    public function updateUser($email, $name, $school, $id){
        $stmt = $this->con->prepare("UPDATE users SET email = ?, name = ?, school = ? where id = ?");
        $stmt->bind_param("sssi", $email, $name, $school, $id);
        if($stmt->execute()){
            return true;
        }
        else{
            return false;
        }
    }

    //update password
    public function updatePassword($currentPassword, $newPasword, $email){
        $hashed_password = $this->getUserPasswordByEmail($email);

        if(password_verify($currentPassword, $hashed_password)){

            $hash_password = password_hash($newPasword, PASSWORD_DEFAULT);
            $stmt->this->con->prepare("UPDATE users SET password = ? WHERE email = ?");
            $stmt->bind_param($hash_password, $email);

            if($stmt->execute()){
                return PASSWORD_CHANGED;
            }
            else{
                return PASSWORD_NOT_CHANGED;
            }
        }
        else{
            return PASSWORD_DO_NOT_MATCH;
        }
    }

    //get all users
    public function getAllUsers(){
        $stmt = $this->con->prepare("SELECT id, email, name, school FROM users");
        $stmt->execute();
        $stmt->bind_result($id, $email, $name, $school);
        $users = array();
        while($stmt->fetch()){            
            $user = array();
            $user['id'] = $id;
            $user['email'] = $email;
            $user['name'] = $name;
            $user['school'] = $school;            
            array_push($users, $user);
        }
        return $users;
    }

    //get userPassword by email
    private function getUserPasswordByEmail($email){
        $stmt = $this->con->prepare("SELECT password FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($password);
        $stmt->fetch();
        return $password;
    }

    //get User by email
    public function getUserByEmail($email){
        $stmt = $this->con->prepare("SELECT id, email, name, school FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->bind_result($id, $email, $name, $school);
        $stmt->fetch();
        $user = array();
        $user['id'] = $id;
        $user['email'] = $email;
        $user['name'] = $name;
        $user['school'] = $school;

        return $user;
    }

    //for check email is unique or not
    private function isEmailExist($email){
        $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        return $stmt->num_rows > 0;
    }
}