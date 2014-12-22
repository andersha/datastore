Realty Data Store
=================

This component stores a document based on a given key, and lets everyone access and delete that document later, if they have the key.

You can use the following operations:

    POST          /store/:key     Create file at key location, put file in multipart and call it 'document'

    GET           /store/:key     Fetch file at key location

    DELETE        /store/:key     Delete file at key location