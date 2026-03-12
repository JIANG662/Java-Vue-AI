<!--页面动作-->
<script setup lang="ts">

import {SwitchButton} from "@element-plus/icons-vue";
</script>


<!--页面元素-->
<template>
  <div class="home">
    <div class="aside">
      <el-card body-style="height: 100%" class="aside-card">
        <div class="aside-out">
          <div class="aside-header">
            <div class="aside-head-title">
              <!--     class="iconfont 图标名称"       -->
              <div style="margin: 0 8px 0 0" class="iconfont icon-lubiao"></div>
              <div class="title-hz">东西部协作</div>
            </div>
            <div>
              <el-divider/>
            </div>
            <div>East-West assistance</div>
          </div>
          <div class="aside-main">
<!-- element-plus的菜单组件默认不开启路由  -->
            <el-menu
                router
                default-active="2"
                class="el-menu-vertical-demo">
              <el-menu-item index="2"> <!--index表示触发哪个路由-->
                <div class="menuClass">
                  <div class="iconfont icon-yonghu iconClass"></div>
                  <div>用户管理</div>
                </div>
              </el-menu-item>
              <el-menu-item index="3">
                <div class="iconfont icon-guanli iconClass"></div>
                <div>项目管理</div>
              </el-menu-item>
              <el-menu-item index="4">
                <div class="iconfont icon-duijie iconClass"></div>
                <div>资源对接</div>
              </el-menu-item>
              <el-menu-item index="/chatAi">
                <div class="iconfont icon-zhushouAvtive iconClass"></div>
                <div>智慧助手</div>
              </el-menu-item>
            </el-menu>
          </div>
          <div class="aside-end">
            <div>
              <el-divider></el-divider>
            </div>
            <div class="aside-bottom">
              <div class="bottom-avatar">
                <el-avatar
                    src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                />
              </div>
              <div class="bottom-right">
                <div style="margin: 0 10px">
                  <div style="font-weight: 600">NoBody</div>
                  <div style="font-size: 13px;color: gray">管理员</div>
                </div>
                <div>
                  <el-dropdown trigger="click">
                     <span class="el-dropdown-link">
                         <el-icon class="el-icon--right"><arrow-down/></el-icon>
                    </span>
                    <template #dropdown>
                      <el-dropdown-menu>
                        <el-dropdown-item :icon="SwitchButton">退出系统</el-dropdown-item>
                          </el-dropdown-menu>
                    </template>
                  </el-dropdown>
                </div>
              </div>

            </div>
          </div>
        </div><!--ctrl alt L格式化代码-->
      </el-card>
    </div>
    <!-- 块元素默认不是100%   -->
    <div class="main">
      <div>
        <el-card style="height:50px">
          <div class="main-header">
            <div>首页</div>
            <div class="iconfont icon-icon_notice"></div>
          </div>
        </el-card>
      </div>
      <div style="height: calc( 100% - 120px ); margin: 10px 0">
        <el-card body-style="height: 100%;padding:0" class="card-main">
          <RouterView></RouterView>
        </el-card>
      </div>
      <div>
        <el-card style="height:50px">
          <div class="main-bottom">@suibianxieshenmedouxing</div>
        </el-card>
      </div>
    </div>

  </div>
</template>


<style scoped>
.home {
  display: flex;
  height: 100%;
  padding: 10px; /*四周内边距都是10像素*/
  box-sizing: border-box; /*保证容器大小不变*/
}

.aside {
  width: 220px;
}

.aside-card {
  height: 100%;
}

.main {
  width: 100%;
  height: 100%;
  margin-left: 20px;
}

.card-main {
  height: 100%;
}

.aside-head-title {
  display: flex;
  align-items: center; /*确保在一条中心线*/
}

.title-hz {
  font-size: 19px;
  font-weight: 600;
}

/* 深度修改 */
:deep(.el-divider--horizontal) {
  margin: 10px 0;
}

.aside-bottom {
  height: 75px;
  display: flex;
  align-items: center;
}

.aside-end {
  width: 100%;
}

.aside-main {
  height: calc(100% - 154px);
}

.aside-out {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100%;
}

.el-menu-vertical-demo {
  border-right: none;
  margin-top: 20px;
}

.menuClass {
  display: flex;
  align-items: center;
}

.iconClass {
  margin-right: 5px;
}
.bottom-right {
  display: flex;
  align-items: center;
}
.main-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: -8px;
}
.main-bottom {
  display: flex;
  margin-top: -8px;
  justify-content: center;
}
</style>