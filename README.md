# direct-debit-update-email-ui-tests

UI test suite for the `Update Email - Direct Debit`

## Pre-requisites

### Services

Prior to executing the tests ensure you have the appropriate [drivers installed](#installing-local-driver-binaries), install [MongoDB](https://docs.mongodb.com/manual/installation/) and install/configure [service manager](https://github.com/hmrc/service-manager).

Run the following command to start services locally:

    sudo mongod
    sm2 --start DD_UPDATE_EMAIL
Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
