/*
 * Copyright (c) 2012-2018 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package com.lm.demo.mqtt;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.ReferenceCountUtil;


import java.io.IOException;

import static io.netty.channel.ChannelFutureListener.CLOSE_ON_FAILURE;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

@Sharable
public class NettyMQTTHandler extends ChannelInboundHandlerAdapter {

//    private static final Logger LOG = LoggerFactory.getLogger(NettyMQTTHandler.class);
//    private final ProtocolProcessor m_processor;

//    public NettyMQTTHandler(ProtocolProcessor processor) {
//        m_processor = processor;
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        MqttMessage msg = (MqttMessage) message;
        if (msg.fixedHeader() == null) {
        	throw new IOException("Unknown packet");
        }
        MqttMessageType messageType = msg.fixedHeader().messageType();

        try {
            switch (messageType) {
                case CONNECT:
//                    m_processor.processConnect(ctx.channel(), (MqttConnectMessage) msg);
                    break;
                case SUBSCRIBE:
//                    m_processor.processSubscribe(ctx.channel(), (MqttSubscribeMessage) msg);
                    break;
                case UNSUBSCRIBE:
//                    m_processor.processUnsubscribe(ctx.channel(), (MqttUnsubscribeMessage) msg);
                    break;
                case PUBLISH:
//                    m_processor.processPublish(ctx.channel(), (MqttPublishMessage) msg);
                    break;
                case PUBREC:
//                    m_processor.processPubRec(ctx.channel(), msg);
                    break;
                case PUBCOMP:
//                    m_processor.processPubComp(ctx.channel(), msg);
                    break;
                case PUBREL:
//                    m_processor.processPubRel(ctx.channel(), msg);
                    break;
                case DISCONNECT:
//                    m_processor.processDisconnect(ctx.channel());
                    break;
                case PUBACK:
//                    m_processor.processPubAck(ctx.channel(), (MqttPubAckMessage) msg);
                    break;
                case PINGREQ:
                case PINGRESP:
                    MqttFixedHeader pingHeader = new MqttFixedHeader(
                            MqttMessageType.PINGRESP,
                            false,
                            AT_MOST_ONCE,
                            false,
                            0);
                    MqttMessage pingResp = new MqttMessage(pingHeader);
                    ctx.writeAndFlush(pingResp);
                    break;
                default:

                    break;
            }
        } catch (Throwable ex) {

            ctx.channel().close().addListener(new ChannelFutureListener() {

                public void operationComplete(ChannelFuture channelFuture) throws Exception {

                }
            });
        } finally {
            //ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        ctx.close().addListener(CLOSE_ON_FAILURE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
;
        ctx.close().addListener(CLOSE_ON_FAILURE);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) {

        ctx.fireChannelWritabilityChanged();
    }

}
