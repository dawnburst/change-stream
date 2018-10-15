/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.mongodb;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListener;

/**
 * Just a simple {@link MessageListener} implementation collecting {@link Message messages} in a {@link BlockingDeque}
 * and printing the message itself to the console.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public class CollectingMessageListener<S, T> implements MessageListener<S, T> {

	private final BlockingDeque<Message<S, T>> messages = new LinkedBlockingDeque<>();
	private final AtomicInteger count = new AtomicInteger();



	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.mongodb.core.messaging.MessageListener#onMessage(org.springframework.data.mongodb.core.messaging.Message)
	 */
	@Override
	public void onMessage(Message<S, T> message) {


//		System.out.println(String.format("Received Message in collection %s.\n\trawsource: %s\n\tconverted: %s",
//				message.getProperties().getCollectionName(), message.getRaw(), message.getBody()));

		System.out.println("Message changed: ");
		System.out.println("Raw: " + message.getRaw());
		System.out.println("Body: " + message.getBody());
		System.out.println("Collection Name: " + message.getProperties().getCollectionName() + " Database name" + message.getProperties().getDatabaseName());



		System.out.println("Full Document:" + message.getBody());

//		ChangeStreamDocument<ChangeStreamDocument> changeStreamDocumentChangeStreamDocument = (ChangeStreamDocument<ChangeStreamDocument>) message.getRaw();
//
//		System.out.println(changeStreamDocumentChangeStreamDocument.getFullDocument());
//
//		ChangeStreamDocument document = changeStreamDocumentChangeStreamDocument.getFullDocument();


		count.incrementAndGet();
		messages.add(message);


	}

	public int messageCount() {
		return count.get();
	}

	public void awaitNextMessages(int count) throws InterruptedException {

		for (int i = 0; i < count; i++) {
			messages.take();
		}
	}
}
