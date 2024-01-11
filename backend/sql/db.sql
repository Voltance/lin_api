-- 接口信息
create table if not exists lin_api.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '用户名',
    `url` text not null comment '请求地址',
    `requestParams` text not null comment '请求参数',
    `method` varchar(256) not null comment '请求类型',
    `requestHeader` text null comment '请求头',
    `responseHeader` varchar(256) null comment '响应头',
    `description` varchar(256) null comment '描述',
    `status` int default 0 not null comment '接口状态(0-关闭, 1-开启)',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息';

insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('胡潇然', 'www.floyd-spinka.co', '蒋浩宇', '萧文轩', '杨昊然', '韦鸿煊', 0, 6452437511);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('贺晓啸', 'www.kelli-schmidt.name', '武天宇', '钟天宇', '罗雨泽', '赖思聪', 0, 5848623756);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('姚峻熙', 'www.hiram-douglas.net', '史博超', '陶鑫鹏', '高晓博', '宋昊天', 0, 15475);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('龚果', 'www.tobias-howe.info', '尹伟泽', '曾文轩', '熊熠彤', '钱烨霖', 0, 76);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('毛明杰', 'www.emory-ryan.name', '邱鸿煊', '于睿渊', '万博涛', '蒋明哲', 0, 5524469092);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('刘思聪', 'www.jim-vonrueden.com', '何凯瑞', '姚煜城', '胡鹏飞', '吕风华', 0, 10165862);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('龚鑫鹏', 'www.milford-reichert.io', '林昊强', '黄展鹏', '黎昊然', '蔡志泽', 0, 27225);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('余浩然', 'www.pinkie-weber.biz', '贺烨磊', '王鑫鹏', '程荣轩', '于立果', 0, 2);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('姜君浩', 'www.kaitlyn-sporer.biz', '贾昊强', '朱博文', '刘鑫鹏', '程明辉', 0, 4956);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('陆思淼', 'www.vance-ortiz.org', '陶煜城', '贺浩轩', '刘鸿煊', '蒋博文', 0, 6);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('王绍辉', 'www.faye-homenick.io', '夏鹤轩', '毛子骞', '萧鑫磊', '陆峻熙', 0, 491);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('陈鹏涛', 'www.raisa-torphy.com', '侯振家', '彭文昊', '雷子骞', '贺明哲', 0, 14);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('张昊然', 'www.norman-langworth.info', '唐明辉', '李锦程', '严智辉', '王天磊', 0, 77755);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('邵鑫鹏', 'www.christopher-schamberger.net', '萧越彬', '崔航', '戴立轩', '龚晋鹏', 0, 876618464);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('丁博文', 'www.cherish-ernser.co', '熊博超', '彭睿渊', '邹思', '朱涛', 0, 9);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('秦立诚', 'www.manuel-toy.org', '萧语堂', '陆天磊', '潘彬', '黎俊驰', 0, 6517);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('陈哲瀚', 'www.buford-leffler.net', '万弘文', '张潇然', '林黎昕', '宋鹤轩', 0, 79);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('段明辉', 'www.jefferson-bruen.io', '严智辉', '姚黎昕', '余哲瀚', '严天翊', 0, 1244626);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('贺胤祥', 'www.tanya-dickens.com', '叶远航', '秦志强', '阎熠彤', '金明杰', 0, 9169551633);
insert into lin_api.`interface_info` (`name`, `url`, `method`, `requestHeader`, `responseHeader`, `description`, `status`, `userId`) values ('杜志强', 'www.vanda-gutmann.net', '傅耀杰', '陶炫明', '冯致远', '毛思远', 0, 2);