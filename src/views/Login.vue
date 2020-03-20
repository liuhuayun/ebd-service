<template>
  <div class="login-container">
    <header class="login-header">
      <div class="logo"></div>
    </header>
    <el-form :model="login"
      @submit.native.prevent
      status-icon
      :rules="rules"
      ref="ruleForm"
      class="login-ruleForm">
      <h2 class="login-title">用户登录</h2>
      <el-form-item prop="username">
        <el-input type="text"
          placeholder="请输入用户名"
          v-model.number="login.username"
          auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
          placeholder="请输入密码"
          v-model="login.password"
          auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item class="login-btn">
        <el-button type="primary"
          native-type="submit"
          @click="submitForm('ruleForm')">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import instance from '../utils/axios';

export default {
  name: 'Login',
  data() {
    return {
      login: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码至少4位', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    ...mapActions(['getUserLoginToken']),
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.getUserLoginToken({
            params: {
              username: _this.login.username,
              password: _this.login.password,
              grant_type: 'password',
              scope: 'all',
            },
            callback: (res) => {
              window.localStorage.setItem('accessToken', res.access_token);
              instance.defaults.headers.Authorization = `Bearer ${res.access_token}`;
              this.$router.push('/');
            },
          });
        } else {
          return false;
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.login-container {
  height: 100vh;
  width: 100vw;
  position: relative;
  background: url("../assets/main0.jpg") no-repeat center;
  background-size: 100%;
  &::after {
    content: "";
    display: block;
    width: 800px;
    height: 400px;
    position: absolute;
    left: 50%;
    top: 20%;
    margin-left: -400px;
    background: url("../assets/mid0.png") no-repeat center;
    background-size: 100%;
  }
}
.login-ruleForm {
  z-index: 9;
  box-sizing: border-box;
  width: 400px;
  position: absolute;
  top: 150px;
  left: 50%;
  margin-left: -200px;
  background-color: #fff;
  border-radius: 5px;
  padding: 15px 50px;
  /deep/.el-form-item {
    margin-top: 25px;
  }
  /deep/.el-form-item__content {
    text-align: center;
  }
  /deep/.el-button {
    width: 100%;
  }
}
.login-title {
  font-size: 16px;
  text-align: center;
  padding: 5px 0;
  color: #409eff;
  border-bottom: 2px solid #409eff;
}
.login-header {
  position: relative;
  height: 50px;
  background: linear-gradient(rgb(92, 127, 172), rgb(0, 63, 140));
  > div {
    position: absolute;
    width: 80px;
    height: 80px;
    left: 20vw;
    top: 0;
    background: url("../assets/logo-m.png") no-repeat center;
    background-size: 100%;
  }
}
</style>
