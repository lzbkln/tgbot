package org.example.tgbot.writers;

import org.example.tgbot.dto.Response;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
public class TelegramWriter {
    public SendMessage createMessage(Response response, Long who) {
        return SendMessage.builder()
                .chatId(who.toString())
                .text(response.data()).build();
    }
}
/*
1. Сделать Request, Response дата классами, но не через record,
record тут не сильно удобен. После задачи 6 создать конструкт для MetaData.

2. На данный момент TelegramWriter создает сообщение, а не пишет его, что плохо.
Возможно, лучше сделать TelegramDataConverter, который объединит текущие функции
TelegramReader и TelegramWriter, то есть read и createMessage. Надо обсудить.

3. Паттерн компонент: Request и Response должны содержать в себе Component[].
Component - это общий класс, от которого наследуются все нужные нам данные.
Это позволит с любыми данными работать одинаково. К тому же
может прийти сообщение с фоткой и текстом, а мы не хотим потерять данные. Надо обсудить.

4. Другое решение задачи 2: реализуем интерфейс Writer нашим ботом, переопределяем метод write,
так, чтобы именно он корректно отправлял сообщение на основе данных. Но тогда придется
конструировать сообщение в этом методе, что опять же плохо. Надо что-то придумать.

5. Еще одно решение задачи 2 и 4: используем паттерн фасад. TelegramWriter делаем фасадом
и создаем разные Sender`ы для разных видов сообщений (MessageSender, PhotoSender, EmojiSender).
Пока остановимся на MessageSender. При этом где-то должны создаваться сами сообщения (пока наверно
в соответствующем Sender`е, либо вынести это в какой-нибудь TelegramCreator),
а затем отправляться через соответствующий Sender TelegramWriter`а. Надо обсудить.

6. Создать класс метаданных и сделать его полем Request, Response.
Либо к Request и Response добавить необходимые поля (id, msgID, username, ...).
маппер: отображение одной сущности в другую, TelegramReader, TelegramWriter сделать

7. На данном этапе должен работать обработчик SimpleHandler, а не TelegramHandler,
поскольку мы хотим лишь эхо ответ, бы обработки команд.
*/


