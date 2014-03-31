/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.addthis.hydra.query.web;

import com.addthis.bundle.core.Bundle;
import com.addthis.bundle.core.BundleField;
import com.addthis.bundle.value.ValueObject;

import static com.addthis.hydra.query.web.HttpUtils.setContentTypeHeader;
import io.netty.channel.ChannelHandlerContext;

class OutputHTML extends AbstractHttpOutput {

    OutputHTML() {
        super();
        setContentTypeHeader(response, "text/html; charset=utf-8");
    }

    @Override
    public void writeStart(ChannelHandlerContext ctx) {
        super.writeStart(ctx);
        ctx.write("<table border=1 cellpadding=1 cellspacing=0>\n");
    }

    @Override
    public void send(ChannelHandlerContext ctx, Bundle row) {
        super.send(ctx, row);
        ctx.write("<tr>");
        for (BundleField field : row.getFormat()) {
            ValueObject o = row.getValue(field);
            ctx.write("<td>" + o + "</td>");
        }
        ctx.write("</tr>\n");
    }

    @Override
    public void sendComplete(ChannelHandlerContext ctx) {
        ctx.write("</table>");
        super.sendComplete(ctx);
    }
}
